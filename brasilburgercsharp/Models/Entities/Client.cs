using brasilburgercsharp.Models.Enums;
using System.ComponentModel.DataAnnotations;

namespace brasilburgercsharp.Models.Entities
{
    public class Client
    {
        public int Id { get; set; }

        [Required]
        [MaxLength(50)]
        public string Nom { get; set; }

        [Required]
        [MaxLength(50)]
        public string Prenom { get; set; }

        [Required]
        [Phone]
        public string Telephone { get; set; }

        [EmailAddress]
        public string? Email { get; set; }

        public string? Adresse { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string MotDePasse { get; set; }

        public RoleEnum Role { get; set; } = RoleEnum.CLIENT;
    }
}
