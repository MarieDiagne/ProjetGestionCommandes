using brasilburgercsharp.Data;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace brasilburgercsharp.Services.Implementations
{
    public class ZoneService : IZoneService
    {
        private readonly ApplicationDbContext _context;
        public ZoneService(ApplicationDbContext context) => _context = context;

        public async Task<IEnumerable<Zone>> GetAllZonesAsync()
        {
            return await _context.Zones.ToListAsync();
        }
    }
}