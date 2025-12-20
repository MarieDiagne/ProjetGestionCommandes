using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Data.Configurations
{
    public class CommandeConfiguration : IEntityTypeConfiguration<Commande>
    {
        public void Configure(EntityTypeBuilder<Commande> builder)
        {
            builder.ToTable("commande");
            builder.Property(e => e.TypeCommande).HasColumnName("type_commande").HasConversion<string>();
            builder.Property(e => e.Etat).HasColumnName("etat").HasConversion<string>();
            builder.Property(e => e.MontantTotal).HasColumnName("montant_total");
            builder.Property(e => e.ClientId).HasColumnName("client_id");
        }
    }
}