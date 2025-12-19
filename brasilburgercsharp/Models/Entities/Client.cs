using System.Collections.Generic;

namespace BrasilBurger.Models.Entities
{
    public class Client : Utilisateur
    {
        public string Adresse { get; set; } = null!;

        // Navigation
        public ICollection<Commande> Commandes { get; set; } = new List<Commande>();
    }
}
