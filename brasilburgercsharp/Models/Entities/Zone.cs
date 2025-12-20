using System.ComponentModel.DataAnnotations;

namespace brasilburgercsharp.Models.Entities
{
    public class Zone
    {
        public int Id { get; set; }

        [Required]
        public string Nom { get; set; }

        public decimal FraisLivraison { get; set; }
    }
}
