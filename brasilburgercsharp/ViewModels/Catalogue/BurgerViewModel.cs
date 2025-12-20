using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.ViewModels.Catalogue
{
    public class BurgerViewModel
    {
        public Burger Burger { get; set; }
        public List<Complement> Frites { get; set; }
        public List<Complement> Boissons { get; set; }
    }
}