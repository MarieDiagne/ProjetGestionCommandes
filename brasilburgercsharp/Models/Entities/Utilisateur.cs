using BrasilBurger.Models.Enums;

namespace BrasilBurger.Models.Entities
{
    public class Utilisateur
    {
        public int Id { get; set; }

        public string Nom { get; set; } = null!;
        public string Prenom { get; set; } = null!;
        public string Telephone { get; set; } = null!;

        public string Email { get; set; } = null!;
        public string Password { get; set; } = null!;

        public RoleEnum Role { get; set; }

        public bool IsActive { get; set; } = true;
    }
}
