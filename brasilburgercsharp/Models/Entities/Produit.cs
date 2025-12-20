using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace brasilburgercsharp.Models.Entities
{
    public abstract class Produit 
    {
        [Key]
        public int Id { get; set; }

        public string Nom { get; set; } = string.Empty;

        public decimal Prix { get; set; }

        // Le ? permet d'éviter l'erreur "Column image is null"
        public string? Image { get; set; } 

        public bool Archive { get; set; } = false;
    }
}