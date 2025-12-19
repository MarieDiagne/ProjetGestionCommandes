using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
    public interface IComplementRepository : IRepository<Complement>
    {
        Task<IEnumerable<Complement>> GetComplementsNonArchivesAsync();
        Task<IEnumerable<Complement>> GetComplementsByTypeAsync(TypeComplementEnum type);
        Task<IEnumerable<Complement>> GetBoissonsAsync();
        Task<IEnumerable<Complement>> GetFritesAsync();
    }
    


    
}