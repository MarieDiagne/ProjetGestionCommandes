using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Models.Entities
{
    public class Complement : Produit
    {
        public TypeComplementEnum Type { get; set; }
    }
}