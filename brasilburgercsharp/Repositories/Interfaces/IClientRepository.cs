using brasilburgercsharp.Models.Entities;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace brasilburgercsharp.Repositories.Interfaces
{
    public interface IClientRepository
    {
        Task<List<Client>> GetAllAsync();
        Task<Client?> GetByIdAsync(int id);
        Task<Client?> GetByTelephoneAsync(string telephone);
        Task AddAsync(Client client);
        Task UpdateAsync(Client client);
        Task DeleteAsync(int id);
    }
}
