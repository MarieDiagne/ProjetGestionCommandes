using brasilburgercsharp.Models.Entities;
using System.Collections.Generic;
using System.Threading.Tasks;
using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Repositories.Interfaces
{
    public interface ICommandeRepository
    {
        Task<List<Commande>> GetAllAsync();
        Task<Commande?> GetByIdAsync(int id);
        Task<List<Commande>> GetByClientIdAsync(int clientId);
        Task<List<Commande>> GetByClientIdAndEtatAsync(int clientId, EtatCommandeEnum etat);
        Task AddAsync(Commande commande);
        Task UpdateAsync(Commande commande);
        Task DeleteAsync(int id);
    }
}
