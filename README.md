# Challenge de Mercado Libre para Android: #ProductsMeli

El siguiente repositorio contiene el desarrollo de una aplicación para dispositivos móviles con SO Android que permite realizar la búsqueda de productos y ver su detalle.

#API Mercado Libre

Se tuvo acceso a la sección de Developers de Mercado Libre donde se pudo observar la estructura de la API de ítems y búsqueda, que permite traer la información de un producto.

```
https://api.mercadolibre.com/sites/$SITE_ID/search?q=PRODUCT
```

#Pantallas de la App

La propuesta que se llevó a cabo fue implementar una serie de pantallas basándome en la aplicación de Mercado Libre, manteniendo su estructura y vista.

- **Pantalla de Splash**: Una pantalla de duración corta que será la introducción y dará inicio a la aplicación.
- **Pantalla de búsqueda del producto**: Esta pantalla se compone de un campo de búsqueda en la parte superior.
- **Pantalla de resultados de la búsqueda**: El usuario podrá visualizar los resultados de la búsqueda por medio de una vista que cuenta con imágenes y datos destacados del producto y que el usuario podrá seleccionar un ítem para más detalle.
- **Pantalla de detalle de producto**: En esta pantalla se mostrará la información del producto seleccionado.

#Arquitectura

La arquitectura que se ideo para este proyecto fue usar un patrón MVVM (Model-View-ViewModel) para facilitar el desarrollo y la separación de la lógica con la interfaz gráfica.







