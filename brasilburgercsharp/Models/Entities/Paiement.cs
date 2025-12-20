using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Models.Entities
{
    public class Paiement
    {
        public int Id { get; set; }
        public DateTime DatePaiement { get; set; } = DateTime.Now;
        public decimal Montant { get; set; }
        public ModePaiementEnum ModePaiement { get; set; }
        public int CommandeId { get; set; }
        
    }
}