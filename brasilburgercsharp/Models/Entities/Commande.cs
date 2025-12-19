using System;
using System.Collections.Generic;
using BrasilBurger.Models.Enums;

namespace BrasilBurger.Models.Entities
{
    public class Commande
    {
        public int Id { get; set; }

        public DateTime DateCommande { get; set; } = DateTime.Now;

        public decimal Montant { get; set; }

        public TypeCommandeEnum TypeCommande { get; set; }
        public EtatCommandeEnum Etat { get; set; } = EtatCommandeEnum.EN_COURS;

        // Client
        public int ClientId { get; set; }
        public Client Client { get; set; } = null!;

        // Navigation
        public ICollection<LigneCommande> Lignes { get; set; } = new List<LigneCommande>();

        public Paiement? Paiement { get; set; }
    }
}
