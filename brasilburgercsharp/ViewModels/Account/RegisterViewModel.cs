using System.ComponentModel.DataAnnotations;

namespace brasilburgercsharp.ViewModels.Account
{
    public class RegisterViewModel
    {
        [Required(ErrorMessage = "Le nom est obligatoire")]
        public string Nom { get; set; } = string.Empty;

        [Required(ErrorMessage = "Le prénom est obligatoire")]
        public string Prenom { get; set; } = string.Empty;

        [Required(ErrorMessage = "L'email est obligatoire")]
        [EmailAddress(ErrorMessage = "Format d'email invalide")]
        public string Email { get; set; } = string.Empty;

        [Required(ErrorMessage = "Le téléphone est obligatoire")]
        [Phone(ErrorMessage = "Format de téléphone invalide")]
        public string Telephone { get; set; } = string.Empty;

        [Required(ErrorMessage = "L'adresse est obligatoire pour les livraisons")]
        [StringLength(255)]
        public string Adresse { get; set; } = string.Empty; // Ajout de l'adresse ici

        [Required(ErrorMessage = "Le mot de passe est obligatoire")]
        [DataType(DataType.Password)]
        [MinLength(6, ErrorMessage = "Le mot de passe doit faire au moins 6 caractères")]
        public string MotDePasse { get; set; } = string.Empty;

        [Required(ErrorMessage = "La confirmation est obligatoire")]
        [DataType(DataType.Password)]
        [Compare("MotDePasse", ErrorMessage = "Les mots de passe ne correspondent pas")]
        public string ConfirmMotDePasse { get; set; } = string.Empty;
    }
}