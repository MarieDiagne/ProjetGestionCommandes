
using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
  


   public interface IPaiementRepository : IRepository<Paiement>
    {
        Task<Paiement?> GetPaiementByCommandeIdAsync(int commandeId);
        Task<IEnumerable<Paiement>> GetPaiementsByModeAsync(ModePaiementEnum mode);

    }
}