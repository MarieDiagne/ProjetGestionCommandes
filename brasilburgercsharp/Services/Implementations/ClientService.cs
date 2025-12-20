using brasilburgercsharp.Data;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Models.Enums;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.ViewModels.Account;
using Microsoft.EntityFrameworkCore;

namespace brasilburgercsharp.Services.Implementations
{
    public class ClientService : IClientService
    {
        private readonly ApplicationDbContext _context;

        public ClientService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<Client?> AuthenticateAsync(string email, string password)
        {
            // Note: En production, utilisez un hashage (BCrypt) pour les mots de passe
            return await _context.Clients
                .FirstOrDefaultAsync(c => c.Email == email && c.MotDePasse == password);
        }

        public async Task<bool> RegisterAsync(RegisterViewModel model)
        {
            if (await _context.Clients.AnyAsync(c => c.Email == model.Email))
                return false;

            var client = new Client
            {
                Nom = model.Nom,
                Prenom = model.Prenom,
                Email = model.Email,
                Telephone = model.Telephone,
                Adresse = model.Adresse,
                MotDePasse = model.MotDePasse, // À hasher en prod
                Role = RoleEnum.CLIENT
            };

            _context.Clients.Add(client);
            return await _context.SaveChangesAsync() > 0;
        }
    }
}