using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace brasilburgercsharp.Models.Entities
{
    public class Burger : Produit
    {
        [Column("description")]
        public string? Description { get; set; }

        
        public virtual ICollection<Menu> Menus { get; set; } = new List<Menu>();
    }
}