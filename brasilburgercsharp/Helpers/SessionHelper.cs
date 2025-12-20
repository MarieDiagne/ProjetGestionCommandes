using System.Text.Json;
using Microsoft.AspNetCore.Http;

namespace brasilburgercsharp.Helpers
{
    public static class SessionHelper
    {
        // On utilise les noms exacts appelés dans PanierService
        public static void SetObjectAsJson(this ISession session, string key, object value)
        {
            session.SetString(key, JsonSerializer.Serialize(value));
        }

        public static T? GetObjectFromJson<T>(this ISession session, string key)
        {
            var value = session.GetString(key);
            return value == null ? default : JsonSerializer.Deserialize<T>(value);
        }
    }
}