using BrasilBurger.Models.Entities;
using BrasilBurger.Helpers;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Data;
using Microsoft.EntityFrameworkCore;

namespace BrasilBurger.Services.Implementations
{
    public class AuthService : IAuthService
    {
        private readonly ApplicationDbContext _context;

        public AuthService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<Client?> Login(string email, string password)
        {
            var client = await _context.Clients.FirstOrDefaultAsync(c => c.Email == email);
            if (client == null) return null;

            return PasswordHasher.Verify(password, client.Password) ? client : null;
        }

        public async Task Register(Client client)
        {
            client.Password = PasswordHasher.Hash(client.Password);
            await _context.Clients.AddAsync(client);
            await _context.SaveChangesAsync();
        }
    }
}
