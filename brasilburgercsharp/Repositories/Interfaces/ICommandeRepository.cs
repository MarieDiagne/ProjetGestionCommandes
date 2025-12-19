using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
    public interface ICommandeRepository : IRepository<Commande>
    {
        Task<IEnumerable<Commande>> GetCommandesByClientIdAsync(int clientId);
        Task<Commande?> GetCommandeWithDetailsAsync(int id);
        Task<IEnumerable<Commande>> GetCommandesByDateAsync(DateTime date);
        Task<IEnumerable<Commande>> GetCommandesByEtatAsync(EtatCommandeEnum etat);
        Task<decimal> GetRecettesJournalieresAsync(DateTime date);
    }
    


    
}