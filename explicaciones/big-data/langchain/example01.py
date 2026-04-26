from langchain.prompts import Prompt
# Definir un prompt básico
prompt = Prompt(
    template="¿Qué es la inteligencia artificial?",
    variables=[]
)
# Generar el texto del prompt
prompt_text = prompt.generate({})
print(prompt_text)

from langchain.models import LanguageModel
# Definir el modelo de lenguaje
model = LanguageModel.from_pretrained("openai/gpt-4o")
# Generar una respuesta utilizando el modelo
response = model.generate(prompt_text)
print(response)
