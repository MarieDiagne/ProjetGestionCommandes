using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Repositories.Interfaces;
using brasilburgercsharp.Data;
using Microsoft.EntityFrameworkCore;

namespace brasilburgercsharp.Repositories.Implementations
{
    public class ZoneRepository : IZoneRepository
    {
        private readonly ApplicationDbContext _context;

        public ZoneRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<List<Zone>> GetAllAsync()
        {
            return await _context.Zones.ToListAsync();
        }

        public async Task<Zone?> GetByIdAsync(int id)
        {
            return await _context.Zones.FindAsync(id);
        }

        public async Task AddAsync(Zone zone)
        {
            await _context.Zones.AddAsync(zone);
            await _context.SaveChangesAsync();
        }

        public async Task UpdateAsync(Zone zone)
        {
            _context.Zones.Update(zone);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAsync(int id)
        {
            var zone = await _context.Zones.FindAsync(id);
            if (zone != null)
            {
                _context.Zones.Remove(zone);
                await _context.SaveChangesAsync();
            }
        }
    }
}
