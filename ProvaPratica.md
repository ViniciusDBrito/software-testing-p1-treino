## Contexto

Bambam A. Stronda abriu uma academia de musculação com foco em resultados, onde os frangos não têm vez. Para
minimizar clientes que vão apenas tirar foto, biscoitar e atrapalhar quem vai malhar, a academia 
**Fast Pump** solicitou um modelo de cobrança baseado em tempo de treino. O valor total de uma sessão de treino é 
calculado por minuto completo, com desconto para minutos realizados fora do horário de pico. O horário de 
funcionamento da academia vai das 5h às 23, não sendo possível começar ou terminar uma sessão de treino 
fora desse horário. Minutos computados antes das 8h ou das 21h em diante recebem 20% de desconto. 
O cálculo do valor de um treino é realizado na classe `SessionPriceCalculator`, a partir dos métodos
`void checkin()` e double `checkout()`. Sempre que os métodos `checkin()` e `checkout()` forem invocados com 
configurações de horário inválidas, deverá ser disparada uma `IllegalStateException`.

Também há uma classe chamada `WorkoutReportService`, responsável por produzir relatórios. Nela, o método 
`averageWorkoutPaidValue(UUID id)` é utilizado para calcular o valor médio pago em treinos por um dado membro da 
academia. Caso o valor de entrada do método seja nulo, será disparada uma `NullPointerException`.

Na avaliação das questões a seguir, não será levado em conta apenas o conjunto de casos de teste, mas também a 
organização da classe de teste, o adequado uso de _frameworks_
e bibliotecas, bem como a qualidade da automação dos
testes.

### Questão 1 [5 pts]: 

Crie um conjunto de casos de testes unitários para o método `checkout()` da classe `SessionPriceCalculator`, 
para cobrir 100% dos critérios da técnica funcional. Antes de automatizar os testes, liste cada 
requisito na tabela a seguir: 

| ID | Requisito de Teste   | Critério                       | 
|----|----------------------|--------------------------------|
| R1 | Exemplo de requisito | Particionamento / Valor Limite |

Indique o(s) id(s) dos requisitos no testes que o(s) cobre(m), no DisplayName ou como comentário.

----

### Questão 2 [5 pts]:

Crie um conjunto de casos de testes unitários para o método `averageWorkoutPaidValue(UUID id)` da classe 
 `WorkoutReportService`, para cobrir 100% dos critérios da técnica funcional. Para isolar a classe
sob teste, emule a interação com o banco de dados. Em ao menos dois casos de teste, crie dublês de teste
do tipo Mock. Utilize métodos e anotações adequados para organizar códigos de fixture. 

Antes de automatizar os testes, liste cada requisito testado na tabela a seguir:

| ID | Requisito de Teste   | Critério                       | 
|----|----------------------|--------------------------------|
| R1 | Exemplo de requisito | Particionamento / Valor Limite |


Indique o(s) id(s) dos requisitos no testes que o(s) cobre(m), no DisplayName ou como comentário.