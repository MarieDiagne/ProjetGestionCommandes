using System.ComponentModel.DataAnnotations.Schema;
using System.Collections.Generic;

namespace brasilburgercsharp.Models.Entities
{
    public class Menu : Produit
    {
        [NotMapped]
        public new decimal Prix { get; set; } 

        [NotMapped]
        public new bool Archive { get; set; }

        public virtual ICollection<Burger> Burgers { get; set; } = new List<Burger>();
    }
}