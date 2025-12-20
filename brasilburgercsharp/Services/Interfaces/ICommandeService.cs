using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.ViewModels.Commande;

namespace brasilburgercsharp.Services.Interfaces
{
    public interface ICommandeService
    {
        Task<int> CreerCommandeAsync(CommandeViewModel model, int clientId);
        Task<IEnumerable<Commande>> GetCommandesByClientAsync(int clientId);
        Task<Commande?> GetDetailsAsync(int id); // Cette ligne doit être présente
    }
}