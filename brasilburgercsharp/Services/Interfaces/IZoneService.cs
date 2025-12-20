using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Services.Interfaces
{
    public interface IZoneService
    {
        Task<IEnumerable<Zone>> GetAllZonesAsync();
    }
}