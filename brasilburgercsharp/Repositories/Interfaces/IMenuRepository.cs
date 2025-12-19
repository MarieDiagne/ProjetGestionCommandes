using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
    public interface IMenuRepository : IRepository<Menu>
    {
        Task<IEnumerable<Menu>> GetMenusNonArchivesAsync();
        Task<Menu?> GetMenuWithCompositionAsync(int id);
        Task<IEnumerable<Menu>> GetMenusWithCompositionsAsync();
    }

    


    
}