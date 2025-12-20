using brasilburgercsharp.Models.Entities;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace brasilburgercsharp.Repositories.Interfaces
{
    public interface IZoneRepository
    {
        Task<List<Zone>> GetAllAsync();
        Task<Zone?> GetByIdAsync(int id);
        Task AddAsync(Zone zone);
        Task UpdateAsync(Zone zone);
        Task DeleteAsync(int id);
    }
}
