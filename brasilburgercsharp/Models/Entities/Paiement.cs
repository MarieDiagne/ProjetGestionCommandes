using System;
using BrasilBurger.Models.Enums;

namespace BrasilBurger.Models.Entities
{
    public class Paiement
    {
        public int Id { get; set; }

        public DateTime DatePaiement { get; set; } = DateTime.Now;

        public decimal Montant { get; set; }

        public ModePaiementEnum ModePaiement { get; set; }

        // Commande (1–1)
        public int CommandeId { get; set; }
        public Commande Commande { get; set; } = null!;
    }
}
