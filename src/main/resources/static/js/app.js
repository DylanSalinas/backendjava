const API = "/productos";
const API_CATEGORIAS = "/categorias";
const API_CARRITO = "/carritos";
const CARRITO_KEY = "carritoId";

let modoBusqueda = "nombre";
let categorias = [];
let carritoId = localStorage.getItem(CARRITO_KEY);

const grid = document.getElementById("grid");
const mensaje = document.getElementById("mensaje");
const modal = document.getElementById("modal");
const modalCarrito = document.getElementById("modalCarrito");
const form = document.getElementById("formProducto");

document.querySelectorAll(".search__tab").forEach((tab) => {
    tab.addEventListener("click", () => {
        document.querySelectorAll(".search__tab").forEach((t) => t.classList.remove("search__tab--active"));
        tab.classList.add("search__tab--active");
        modoBusqueda = tab.dataset.mode;
        document.getElementById("inputBuscar").placeholder =
            modoBusqueda === "id" ? "Ingrese el ID del producto..." : "Buscar por nombre...";
    });
});

document.getElementById("btnBuscar").addEventListener("click", buscar);
document.getElementById("btnLimpiar").addEventListener("click", () => {
    document.getElementById("inputBuscar").value = "";
    cargarProductos();
});
document.getElementById("btnNuevo").addEventListener("click", () => abrirModal());
document.getElementById("btnCarrito").addEventListener("click", abrirCarrito);
document.getElementById("btnCerrarCarrito").addEventListener("click", () => modalCarrito.close());
document.getElementById("btnVaciarCarrito").addEventListener("click", vaciarCarrito);
document.getElementById("btnCerrar").addEventListener("click", cerrarModal);
document.getElementById("btnCancelar").addEventListener("click", cerrarModal);
document.getElementById("imagenUrl").addEventListener("input", actualizarPreview);
form.addEventListener("submit", guardarProducto);

document.getElementById("inputBuscar").addEventListener("keydown", (e) => {
    if (e.key === "Enter") buscar();
});

async function fetchJson(url, options = {}) {
    const res = await fetch(url, {
        headers: { "Content-Type": "application/json" },
        ...options,
    });

    if (!res.ok) {
        const texto = await res.text();
        throw new Error(texto || `Error ${res.status}`);
    }

    if (res.status === 204) return null;
    return res.json();
}

function mostrarMensaje(texto, tipo = "ok") {
    mensaje.textContent = texto;
    mensaje.className = `mensaje mensaje--${tipo}`;
    mensaje.hidden = false;
    setTimeout(() => { mensaje.hidden = true; }, 3500);
}

function formatearPrecio(precio) {
    return new Intl.NumberFormat("es-AR", { style: "currency", currency: "USD" }).format(precio);
}

function imagenFallback() {
    return "data:image/svg+xml," + encodeURIComponent(
        '<svg xmlns="http://www.w3.org/2000/svg" width="200" height="160" viewBox="0 0 200 160">' +
        '<rect fill="#f3f4f6" width="200" height="160"/>' +
        '<text x="100" y="85" text-anchor="middle" fill="#9ca3af" font-size="14">Sin imagen</text></svg>'
    );
}

function renderProductos(productos) {
    if (!productos.length) {
        grid.innerHTML = '<p class="empty">No se encontraron productos.</p>';
        return;
    }

    grid.innerHTML = productos.map((p) => `
        <article class="card">
            <img class="card__img" src="${p.imagenUrl || imagenFallback()}" alt="${p.nombre}"
                 onerror="this.src='${imagenFallback()}'">
            <div class="card__body">
                <h3 class="card__title">${p.nombre}</h3>
                <p class="card__meta">ID: ${p.id} · ${p.categoria?.nombre || "Sin categoria"}</p>
                <p class="card__meta">Stock: ${p.stock}</p>
                <p class="card__price">${formatearPrecio(p.precio)}</p>
                <div class="card__actions">
                    <button class="btn btn--secondary" onclick="verDetalle(${p.id})">Ver</button>
                    <button class="btn btn--primary" onclick="editarProducto(${p.id})">Editar</button>
                    <button class="btn btn--danger" onclick="eliminarProducto(${p.id})">Eliminar</button>
                    <button class="btn btn--primary btn--cart-add" onclick="agregarAlCarrito(${p.id})"
                        ${p.stock <= 0 ? "disabled" : ""}>
                        ${p.stock <= 0 ? "Sin stock" : "Agregar al carrito"}
                    </button>
                </div>
            </div>
        </article>
    `).join("");
}

async function cargarProductos() {
    try {
        const productos = await fetchJson(API);
        renderProductos(productos);
    } catch (err) {
        mostrarMensaje("No se pudieron cargar los productos. ¿Está corriendo la app?", "error");
    }
}

async function buscar() {
    const valor = document.getElementById("inputBuscar").value.trim();
    if (!valor) {
        cargarProductos();
        return;
    }

    try {
        if (modoBusqueda === "id") {
            const producto = await fetchJson(`${API}/${valor}`);
            renderProductos([producto]);
        } else {
            const productos = await fetchJson(`${API}/nombre/${encodeURIComponent(valor)}`);
            renderProductos(productos);
        }
    } catch {
        renderProductos([]);
        mostrarMensaje("Producto no encontrado.", "error");
    }
}

async function cargarCategorias() {
    categorias = await fetchJson(API_CATEGORIAS);
    const select = document.getElementById("categoriaId");
    select.innerHTML = categorias.map((c) =>
        `<option value="${c.id}">${c.nombre}</option>`
    ).join("");
}

function abrirModal(titulo = "Nuevo producto") {
    document.getElementById("modalTitulo").textContent = titulo;
    modal.showModal();
}

function cerrarModal() {
    modal.close();
    form.reset();
    document.getElementById("productoId").value = "";
    document.getElementById("preview").hidden = true;
}

function actualizarPreview() {
    const url = document.getElementById("imagenUrl").value.trim();
    const preview = document.getElementById("preview");
    const img = document.getElementById("previewImg");

    if (url) {
        img.src = url;
        preview.hidden = false;
    } else {
        preview.hidden = true;
    }
}

function armarBody() {
    return {
        nombre: document.getElementById("nombre").value.trim(),
        descripcion: document.getElementById("descripcion").value.trim(),
        precio: parseFloat(document.getElementById("precio").value),
        stock: parseInt(document.getElementById("stock").value, 10),
        imagenUrl: document.getElementById("imagenUrl").value.trim() || null,
        categoria: { id: parseInt(document.getElementById("categoriaId").value, 10) },
    };
}

async function guardarProducto(e) {
    e.preventDefault();

    const id = document.getElementById("productoId").value;
    const body = armarBody();

    if (body.precio <= 0) {
        mostrarMensaje("El precio debe ser mayor que cero.", "error");
        return;
    }
    if (body.stock < 0) {
        mostrarMensaje("El stock no puede ser negativo.", "error");
        return;
    }

    try {
        if (id) {
            await fetchJson(`${API}/${id}`, { method: "PUT", body: JSON.stringify(body) });
            mostrarMensaje("Producto actualizado.");
        } else {
            await fetchJson(API, { method: "POST", body: JSON.stringify(body) });
            mostrarMensaje("Producto creado.");
        }
        cerrarModal();
        cargarProductos();
    } catch (err) {
        mostrarMensaje(err.message, "error");
    }
}

async function verDetalle(id) {
    try {
        const p = await fetchJson(`${API}/${id}`);
        abrirModal(`Detalle · ${p.nombre}`);
        document.getElementById("productoId").value = p.id;
        document.getElementById("nombre").value = p.nombre;
        document.getElementById("descripcion").value = p.descripcion || "";
        document.getElementById("precio").value = p.precio;
        document.getElementById("stock").value = p.stock;
        document.getElementById("categoriaId").value = p.categoria?.id || "";
        document.getElementById("imagenUrl").value = p.imagenUrl || "";
        actualizarPreview();
    } catch {
        mostrarMensaje("No se pudo cargar el producto.", "error");
    }
}

async function editarProducto(id) {
    await verDetalle(id);
    document.getElementById("modalTitulo").textContent = "Editar producto";
}

async function eliminarProducto(id) {
    if (!confirm("¿Eliminar este producto?")) return;

    try {
        await fetchJson(`${API}/${id}`, { method: "DELETE" });
        mostrarMensaje("Producto eliminado.");
        cargarProductos();
    } catch {
        mostrarMensaje("No se pudo eliminar el producto.", "error");
    }
}

window.verDetalle = verDetalle;
window.editarProducto = editarProducto;
window.eliminarProducto = eliminarProducto;
window.agregarAlCarrito = agregarAlCarrito;

async function inicializarCarrito() {
    if (carritoId) {
        try {
            await fetchJson(`${API_CARRITO}/${carritoId}`);
            await actualizarCarrito();
            return;
        } catch {
            localStorage.removeItem(CARRITO_KEY);
            carritoId = null;
        }
    }

    const carrito = await fetchJson(API_CARRITO, { method: "POST" });
    carritoId = carrito.id;
    localStorage.setItem(CARRITO_KEY, carritoId);
    actualizarBadge(0);
}

function contarItems(carrito) {
    if (!carrito?.productos?.length) return 0;
    return carrito.productos.reduce((sum, cp) => sum + cp.cantidad, 0);
}

function actualizarBadge(cantidad) {
    const badge = document.getElementById("cartBadge");
    badge.textContent = cantidad;
    badge.classList.toggle("cart-badge--empty", cantidad === 0);
}

function renderCarrito(carrito) {
    const content = document.getElementById("cartContent");
    const totalEl = document.getElementById("cartTotal");

    if (!carrito.productos?.length) {
        content.innerHTML = '<p class="cart-empty">Tu carrito esta vacio.</p>';
        totalEl.textContent = "Total: " + formatearPrecio(0);
        actualizarBadge(0);
        return;
    }

    let total = 0;
    content.innerHTML = carrito.productos.map((cp) => {
        const p = cp.producto;
        const subtotal = p.precio * cp.cantidad;
        total += subtotal;
        return `
            <div class="cart-item">
                <img class="cart-item__img" src="${p.imagenUrl || imagenFallback()}" alt="${p.nombre}"
                     onerror="this.src='${imagenFallback()}'">
                <div class="cart-item__info">
                    <p class="cart-item__name">${p.nombre}</p>
                    <p class="cart-item__meta">Cantidad: ${cp.cantidad} · ${formatearPrecio(p.precio)} c/u</p>
                    <p class="cart-item__meta">Subtotal: ${formatearPrecio(subtotal)}</p>
                </div>
            </div>
        `;
    }).join("");

    totalEl.textContent = "Total: " + formatearPrecio(total);
    actualizarBadge(contarItems(carrito));
}

async function actualizarCarrito() {
    if (!carritoId) return;
    const carrito = await fetchJson(`${API_CARRITO}/${carritoId}`);
    renderCarrito(carrito);
}

async function abrirCarrito() {
    try {
        await actualizarCarrito();
        modalCarrito.showModal();
    } catch {
        mostrarMensaje("No se pudo cargar el carrito.", "error");
    }
}

async function agregarAlCarrito(productoId) {
    try {
        if (!carritoId) await inicializarCarrito();
        await fetchJson(`${API_CARRITO}/${carritoId}/productos/${productoId}`, { method: "POST" });
        mostrarMensaje("Producto agregado al carrito.");
        await actualizarCarrito();
        cargarProductos();
    } catch (err) {
        mostrarMensaje(err.message, "error");
    }
}

async function vaciarCarrito() {
    if (!carritoId) return;
    if (!confirm("¿Vaciar el carrito?")) return;

    try {
        await fetchJson(`${API_CARRITO}/${carritoId}/vaciar`, { method: "DELETE" });
        mostrarMensaje("Carrito vaciado.");
        await actualizarCarrito();
        cargarProductos();
    } catch (err) {
        mostrarMensaje(err.message, "error");
    }
}

cargarCategorias()
    .then(inicializarCarrito)
    .then(cargarProductos);
