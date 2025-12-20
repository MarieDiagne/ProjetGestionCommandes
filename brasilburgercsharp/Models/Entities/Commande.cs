using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Models.Entities
{
    public class Commande
    {
        public int Id { get; set; }
        public DateTime DateCommande { get; set; } = DateTime.Now;
        public TypeCommandeEnum TypeCommande { get; set; }
        public EtatCommandeEnum Etat { get; set; } = EtatCommandeEnum.VALIDEE;
        public decimal MontantTotal { get; set; }
        public int ClientId { get; set; }
        public int? ZoneId { get; set; }
        
        public virtual Client? Client { get; set; }
        public virtual Zone? Zone { get; set; }
        public virtual ICollection<LigneCommande> Lignes { get; set; } = new List<LigneCommande>();
    }
}