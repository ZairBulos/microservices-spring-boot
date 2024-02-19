# Microservices - Spring Boot

![](/documentation/images/application.png)

## Monolitos y Microservicios, ¿cuál elegir?

### Introducción

Uno de los debates más comunes entre desarrolladores gira alrededor de las dos principales arquitecturas para el desarrollo de una aplicación web:
Monolitos vs Microservicios.

Monolitos que viene de monolito, o roca, un concepto que se usa para describir a bloques de roca de gran tamaño, y que se asemejan a las arquitecturas donde todos los servicios de un sistema están en un mismo bloque, contra microservicios, una alternativa donde cada servicio se desarrolla por separado, y el sistema completo es una colección de servicios que se comunican entre sí.

Cada uno de los enfoques ofrece ventajas y desventajas por sobre el otro, y como en la mayoría de las discusiones técnicas, no hay una respuesta definitiva para la pregunta ¿cuál es la mejor arquitectura? Cada problema plantea un escenario con características para las que una arquitectura puede ser mejor que la otra.

### ¿Qué son los microservicios?

Los microservicios son una arquitectura de desarrollo de software que ha crecido en popularidad en los últimos años. Este enfoque se basa en el principio de “divide y vencerás”, en esta arquitectura una aplicación se divide en pequeños servicios independientes, cada uno cumpliendo una función específica. Este enfoque contrasta con el de los monolitos donde todas las funcionalidades residen en un único bloque de código, los microservicios ofrecen una alternativa modular y distribuida.

En esencia, un microservicio es un componente autónomo que se centra en realizar una tarea específica dentro de la aplicación global. Estos servicios están diseñados para ser independientes entre sí, lo que significa que pueden ser desarrollados, desplegados y escalados de manera independiente. Cada microservicio se comunica con otros a través de interfaces bien definidas, generalmente a través de protocolos de comunicación como HTTP o gRPC.

Las características clave de los microservicios incluyen la independencia de implementación, lo que permite a los equipos trabajar de manera aislada en cada servicio, y la capacidad de escalar horizontalmente según las necesidades de carga específicas de cada componente. Esto facilita la implementación de actualizaciones y mejoras sin afectar todo el sistema, ya que cada microservicio puede evolucionar de forma independiente.

La adopción de microservicios también proporciona una mayor flexibilidad en términos de tecnologías utilizadas para cada servicio. Los equipos pueden seleccionar las herramientas y lenguajes de programación más adecuados para cada tarea, siempre y cuando se respeten las interfaces de comunicación establecidas.

A pesar de estas ventajas, la arquitectura de microservicios no está exenta de desafíos. La complejidad en la gestión de múltiples servicios, la necesidad de una infraestructura robusta para el descubrimiento y la comunicación entre servicios, así como la necesidad de un diseño resiliente que permita la operación del sistema aún cuando un microservicio falle.

### ¿Qué son los monolitos?

Los monolitos representan una arquitectura tradicional en el desarrollo de aplicaciones web, caracterizada por consolidar todas las funcionalidades de un sistema en un único bloque de código. A diferencia de los microservicios, donde cada función se implementa de manera independiente, los monolitos son estructuras más compactas y centralizadas.

En un monolito, todas las partes de una aplicación, como la interfaz de usuario, la lógica de negocio y la persistencia de datos, coexisten en la misma base de código. Esta simplicidad estructural puede facilitar el desarrollo inicial y la comprensión global del sistema, ya que todas las partes están directamente interconectadas. Además, los monolitos suelen ser más sencillos de implementar, desplegar y gestionar, ya que toda la aplicación se ejecuta como una entidad única.

La arquitectura de monolitos ha sido históricamente la elección predeterminada para muchos proyectos debido a su simplicidad aparente. Sin embargo, a medida que las aplicaciones crecen en tamaño y complejidad, los monolitos pueden presentar desafíos significativos. La modificación de una parte del código puede afectar de forma inesperada otras partes del sistema, lo que puede dificultar la implementación de nuevas características, o la implementación de soluciones a problemas y bugs.

La escalabilidad también puede ser un desafío en los monolitos, ya que toda la aplicación debe ser escalada verticalmente, es decir, aumentando los recursos de la máquina que ejecuta el monolito. Esto puede resultar costoso y menos eficiente en comparación con la escalabilidad horizontal que ofrecen los microservicios.

Si bien durante los últimos años, la arquitectura de monolitos ha sido sujeta de cuestionamientos sobre si puede satisfacer las demandas de aplicaciones modernas altamente escalables y distribuida, existen muchas aplicaciones de gran escala que siguen utilizando este enfoque. En los últimos meses hemos visto este discurso ser ajustado para preguntarnos si realmente las aplicaciones modernas requieren una arquitectura de microservicios o no.

### Despliegue a producción, ¿qué cambia?

El despliegue a producción es un aspecto crítico en el ciclo de vida de cualquier aplicación, y las diferencias fundamentales entre las arquitecturas de monolitos y microservicios se reflejan en la forma en que abordan este proceso.

#### Monolitos:

La implementación ocurre de manera unitaria, ya que toda la aplicación se desarrolla sobre la misma base de código. Esto simplifica el proceso de despliegue, ya que no hay necesidad de coordinar múltiples servicios.

No existe un problema de coordinación de versiones, como posiblemente suceda con los microservicios, luego de que todos los componentes comparten el mismo código, esto minimiza los conflictos por gestión de versiones.

El despliegue de monolitos suele requerir una orquestación menos compleja, ya que todas las dependencias están presentes en un único entorno. Sin embargo, esto también implica que cualquier error en una parte del sistema puede tener impactos en la totalidad de la aplicación.

#### Microservicios:

cada servicio puede ser desarrollado, probado y desplegado de manera independiente. Esto permite a los equipos actualizar servicios sin afectar la totalidad de la aplicación. 

La independencia en el despliegue permite que apliquemos algunas metodologías populares como integración continua o despliegue continuo, muy comúnes en equipos de desarrollo que implementan metodologías agiles.

Por otr lado, la existencias de múltiples servicios implica que exista coordinación de versiones entre ellos, luego de que cada microservicio tiene su propio ciclo de vida, es posible que uno dependa de una versión distinta a la que uno de los servicios está implementando.

El despliegue de microservicios generalmente requiere una orquestación más sofisticada. Herramientas como Kubernetes o Docker Swarm se utilizan comúnmente para gestionar la implementación, escalado y actualización de servicios. Esta complejidad adicional es necesaria para mantener la coherencia y la disponibilidad del sistema.

En resumen, la elección entre monolitos y microservicios en el despliegue depende de las necesidades específicas del proyecto. *Monolitos ofrecen simplicidad, coherencia*; *microservicios brindan independencia y escalabilidad*.

### Es hora de escalar, ¿cuál elijo? 

La capacidad de un sistema para escalar eficientemente, y manejar altas cargas de tráfico se ha convertido en el tema central del debate entre microservicios y monolitos.

En este sentido existen dos principales áreas de análisis: hasta dónde puede escalar (viabilidad), y cómo se aprovechan los recursos de la infraestructura (eficiencia).

#### Eficiencia:

Una arquitectura de microservicios permite que cada componente del sistema escale de manera independiente y de acuerdo a sus necesidades, en ese sentido, el proceso de escalar puede ser más sencillo, en contra de los monolitos.

En el caso de los monolitos, el proceso de escala es vertical y menos eficiente, todos los componentes viven en la misma base de código y comparten infraestructura, por lo que, no es posible considerar las necesidades de cada componente en el proceso de escalar la infraestructura.

Esto puede implicar que la infraestructura de un monolito sea excesiva para las necesidades de algunos componentes y ajustada para otros, mientras que para los componentes de un monolito la infraestructura es la adecuada para cada componente.

#### Viabilidad:

Una de las principales razones por las que una organización decide migrar de un monolito hacia una arquitectura de microservicios responde a la viabilidad de que el monolito soporte la escala actual de la compañía.

Y aunque como ya hemos analizado, es más eficiente escalar una arquitectura de microservicios, esto no significa que los monolitos funcionen solo para aplicaciones de baja escala y poco tráfico.

Existen grandes proyectos con millones de usuarios implementados sobre monolitos, así como algunos más pequeños y de menor escala que usan microservicios.

En muchas ocasiones, las y los expertos apuntan al tamaño de la empresa, y no al de la escala, para determinar qué arquitectura usar. 

La arquitectura de microservicios responde mejor a organizaciones grandes, donde cada equipo puede implementar la funcionalidad de un componente a su mejor criterio, sin la necesidad de coordinar con otros equipos, mientras que en el caso de los monolitos, todos los equipos deben compartir el mismo contexto y las mismas reglas en el desarrollo del proyecto, aumentando la necesidad de coordinación, reuniones, y comunicación entre equipos.

Por otro lado, para un equipo mediano a pequeño, una arquitectura de monolito puede eficientizar el desarrollo de nuevas características, ya que algunos miembros del equipo pueden necesitar contexto de la operación de múltiples componentes del sistema.

En resumen, escalar eficientemente para altas cargas de tráfico, y para empresas de gran tamaño, suelen ser las principales razones para migrar de monolitos a microservicios, mientras que también, podemos decir que un equipo pequeño, sin importar la escala de tráfico que maneja, puede beneficiarse de una arquitectura de monolito.

### El rol de la nube

En el ámbito del despliegue a producción, el papel de la nube es crucial y marca una distinción notable entre las arquitecturas de monolitos y microservicios.

#### Monolitos en la Nube:

Cuando se trata de monolitos, el despliegue sigue siendo unitario, pero la nube ofrece oportunidades para optimizar recursos. La capacidad de escalar verticalmente en entornos basados en la nube permite ajustar los recursos de manera más eficiente, aunque aún se comparte la misma base de código.

La coordinación de versiones puede ser menos compleja, pero la nube proporciona herramientas para gestionar actualizaciones y rollbacks de manera más flexible, facilitando el mantenimiento de la coherencia en el despliegue.

#### Microservicios en la Nube:

En el contexto de los microservicios, la independencia en el despliegue encuentra un aliado poderoso en la nube. Cada servicio puede aprovechar los recursos de manera autónoma, escalando horizontalmente según las demandas específicas de cada componente.

La nube facilita la implementación de metodologías ágiles como la integración continua y despliegue continuo, permitiendo una evolución constante de los servicios de forma independiente.

La coordinación de versiones se vuelve más esencial en entornos basados en la nube, ya que cada microservicio puede tener su propio ciclo de vida. Herramientas de orquestación como Kubernetes se convierten en aliados clave para gestionar la complejidad del despliegue.

En resumen, la elección entre monolitos y microservicios en la nube se centra en cómo aprovechar al máximo las capacidades escalables y flexibles de estos entornos. *Monolitos pueden beneficiarse de la escalabilidad vertical*, mientras que los *microservicios encuentran en la nube un terreno propicio para su independencia y agilidad*.

## Enlaces útiles

- [Microservicios con Spring Boot](https://youtube.com/playlist?list=PLlYjHWCxjWmAt5hE3OEaemlWkZBZa7w4e&si=PMvXzQEpUo0nTQCw)
