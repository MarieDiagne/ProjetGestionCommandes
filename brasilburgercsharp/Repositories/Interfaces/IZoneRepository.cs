using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
    {
        public interface IZoneRepository : IRepository<Zone>
        {
            Task<IEnumerable<Zone>> GetAllZonesAsync();
            Task<Zone?> GetZoneByNomAsync(string nom);
        


        }
    }