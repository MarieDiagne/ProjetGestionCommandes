using Microsoft.EntityFrameworkCore;
using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options) { }

        public DbSet<Client> Clients { get; set; }
        public DbSet<Commande> Commandes { get; set; }
        public DbSet<Burger> Burgers { get; set; }
        public DbSet<Menu> Menus { get; set; }
        public DbSet<Complement> Complements { get; set; }
        public DbSet<Paiement> Paiements { get; set; }
        public DbSet<LigneCommande> LignesCommandes { get; set; }
        public DbSet<Zone> Zones { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // On définit les tables manuellement sans stratégie d'héritage globale
            modelBuilder.Entity<Burger>().ToTable("burger");
            modelBuilder.Entity<Menu>().ToTable("menu");
            modelBuilder.Entity<Client>().ToTable("client");
            modelBuilder.Entity<Complement>().ToTable("complement");
            modelBuilder.Entity<LigneCommande>().ToTable("ligne_commande");
            modelBuilder.Entity<Paiement>().ToTable("paiement");
            modelBuilder.Entity<Zone>().ToTable("zone");
            modelBuilder.Entity<Commande>().ToTable("commande");

            // Relation Many-to-Many via la table composition
            modelBuilder.Entity<Menu>()
                .HasMany(m => m.Burgers)
                .WithMany(b => b.Menus)
                .UsingEntity<Dictionary<string, object>>(
                    "composition",
                    j => j.HasOne<Burger>().WithMany().HasForeignKey("burger_id"),
                    j => j.HasOne<Menu>().WithMany().HasForeignKey("menu_id")
                );

            // Mapping automatique snake_case pour PostgreSQL
            foreach (var entity in modelBuilder.Model.GetEntityTypes())
            {
                if (entity.GetTableName() == "composition") continue;
                foreach (var property in entity.GetProperties())
                {
                    var name = property.GetColumnBaseName();
                    var snakeCaseName = string.Concat(name.Select((x, i) => i > 0 && char.IsUpper(x) ? "_" + x.ToString() : x.ToString())).ToLower();
                    property.SetColumnName(snakeCaseName);
                }
            }
        }
    }
}