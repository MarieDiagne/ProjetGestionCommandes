using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Repositories.Interfaces
{
    public interface IComplementRepository
    {
        Task<IEnumerable<Complement>> GetAllAsync();
        Task<Complement?> GetByIdAsync(int id);
    }
}