using Microsoft.EntityFrameworkCore;
using BrasilBurger.Models.Entities;

namespace BrasilBurger.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions options) : base(options) {}

        public DbSet<Client> Clients => Set<Client>();
        public DbSet<Commande> Commandes => Set<Commande>();
        public DbSet<LigneCommande> LigneCommandes => Set<LigneCommande>();
        public DbSet<Paiement> Paiements => Set<Paiement>();
        public DbSet<Burger> Burgers => Set<Burger>();
        public DbSet<Menu> Menus => Set<Menu>();
        public DbSet<Complement> Complements => Set<Complement>();
    }
}
