@model IEnumerable<BrasilBurger.Models.Entities.Menu>

<h2>Menus</h2>

@foreach (var m in Model)
{
    <p>
        <strong>@m.Nom</strong> - @m.Prix FCFA
        <a href="/Catalogue/DetailsMenu/@m.Id">Détails</a>
    </p>
}
