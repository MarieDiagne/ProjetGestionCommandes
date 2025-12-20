using brasilburgercsharp.Models.Enums;
using System.ComponentModel.DataAnnotations;

namespace brasilburgercsharp.ViewModels.Commande
{
    public class CommandeViewModel
    {
        [Required(ErrorMessage = "Veuillez choisir un type de commande")]
        public TypeCommandeEnum TypeCommande { get; set; }

        public int? ZoneId { get; set; }

        [Required(ErrorMessage = "Veuillez choisir un mode de paiement")]
        public ModePaiementEnum ModePaiement { get; set; } // Cette ligne manquait
    }
}