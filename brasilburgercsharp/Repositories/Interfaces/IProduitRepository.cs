using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Repositories.Interfaces
{
    public interface IProduitRepository
    {
        Task<IEnumerable<Produit>> GetAllAsync();
        Task<Produit?> GetByIdAsync(int id);
        Task AddAsync(Produit produit);
    }
}