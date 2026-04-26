```
EN DESARROLLO!!!
```

# LangChain

Enlaces de interés:
* [LangChain para Dummies: Guía Completa desde la Instalación hasta su Utilización](https://devjaime.medium.com/langchain-para-dummies-gu%C3%ADa-completa-desde-la-instalaci%C3%B3n-hasta-su-utilizaci%C3%B3n-8dc3df92f048)
* [Página oficial](https://python.langchain.com/v0.2/docs/introduction/)

LangChain es un framework de Python para desarrollar aplicaciones con modelos de lenguaje grandes (LLM) integrados.

## Instalación

Crear un entorno virtual para instalar el paquete de Python:

```python
python3 -m venv venv
source venv/bin/activate
pip install langchain
```

## Ejemplo 1

* Crear un `prompt`.
* Elegir un modelo de lenguaje.

## Obtener API KEY para Gemini

* Iniciar sesión con tu cuenta de Google
* Ir a Google AI Studio (https://aistudio.google.com) -> Get API KEY
* Crear clave de API. Copiar el contenido en un fichero privado en local.

Probar la API KEY
```
> GEMINI_API_KEY=XXX
> 

curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent" \
  -H 'Content-Type: application/json' \
  -H 'X-goog-api-key: GEMINI_API_KEY' \
  -X POST \
  -d '{
    "contents": [
      {
        "parts": [
          {
            "text": "Explain how AI works in a few words"
          }
        ]
      }
    ]
  }'
```


