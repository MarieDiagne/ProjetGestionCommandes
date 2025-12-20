using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Services.Interfaces
{
    public interface IProduitService
    {
        Task<IEnumerable<Produit>> GetCatalogueAsync(string type);
        Task<Burger?> GetBurgerDetailsAsync(int id);
        Task<IEnumerable<Complement>> GetAvailableComplementsAsync();
        // Ne rajoutez rien d'autre ici pour l'instant
    }
}