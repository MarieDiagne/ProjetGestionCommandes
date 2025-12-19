using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
    public interface IBurgerRepository : IRepository<Burger>
    {
        Task<IEnumerable<Burger>> GetBurgersNonArchivesAsync();
        Task<Burger?> GetBurgerWithDetailsAsync(int id);
    }



}