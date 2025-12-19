using BrasilBurger.Models.Entities;

namespace BrasilBurger.Services.Interfaces
{
    public interface ICommandeService
    {
        Task<IEnumerable<Commande>> GetByClient(int clientId);
    }
}
