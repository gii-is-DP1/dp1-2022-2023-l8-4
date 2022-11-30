<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
            <ul class = "list-group"> <!-- Esto debe de dinamizarse con un bucle ForEach -->
                    <li class="list-group-item">
                         <img class = "profilePicture" src="/resources/images/logo.png"/> <!-- Tener en cuenta añadir tanto la ruta como el .png -->
                         <div class="alert alert-success" role="alert"> <!-- La puntuación debería de llegar del backend también -->
                            5
                         </div>
                     </li>
                     <li class="list-group-item">
                            <img class = "profilePicture" src="/resources/images/logo.png"/>
                            <div class="alert alert-success" role="alert">
                                0
                             </div>
                     </li>
                     <li class="list-group-item">
                             <img class = "profilePicture" src="/resources/images/logo.png"/>
                             <div class="alert alert-success" role="alert">
                                15
                             </div>
                     </li>
            </ul>
            <div class="card" style="width: 18rem; float: right;"> <!-- NO TOCAR -->
              <div class="card-body" id = "mycard">
              </div>
            </div>
            <div class="card" style="width: 18rem; float: right;"> <!-- NO TOCAR -->
                <div class="card-body" id = "middlecard">
                </div>
            </div>
</petclinic:layout>


        <script>
            function loadSymbols( className, stringOfSymbols , deskId )
            {
                const symbols = stringOfSymbols.split( " " );
                for( let i = 0 ; i < symbols.length ; i += 2 )
                {
                            let img_figure_left = document.createElement( "img" );
                            let img_figure_right = document.createElement( "img" );

                            img_figure_left.setAttribute( "src", "/resources/images/animals/" +  symbols[i] + ".png" )
                            img_figure_right.setAttribute( "src", "/resources/images/animals/" +  symbols[i+1] + ".png" )

                            img_figure_left.setAttribute( "class", className );
                            img_figure_left.setAttribute( "id", symbols[i] );
                            img_figure_right.setAttribute( "class", className );
                            img_figure_right.setAttribute( "id", symbols[i+1] );

                            document.getElementById( deskId ).appendChild( img_figure_left );
                            document.getElementById( deskId ).appendChild( img_figure_right );
                }
            }

            // Load cards
            loadSymbols( "own_symbols", "ABEJA GORRINO AGUILA DELFIN LOBO LEON PERRO PAVOREAL", "mycard" ); // El listado de nombres debe de sustituirse por la cadena de figuras que venga del backend
            loadSymbols( "middle_symbols", "ABEJA GORRINO AGUILA DELFIN ORCA LEON PERRO PAVOREAL", "middlecard" ); // Idem con las cartas del backend


            // Onclick event for each symbol of a card
            Array.from( document.getElementsByClassName( "own_symbols" ) )
                    .forEach( ( symbol ) => {
                                                symbol.addEventListener( "click", () =>
                                                                                        {
                                                                                            const figure = symbol.getAttribute( "id" );
                                                                                            const middleCard = document.getElementsByClassName( "middle_symbols" );
                                                                                            for ( let j = 0 ; j < middleCard.length ; j++ )
                                                                                           {
                                                                                                if ( figure == middleCard[j].getAttribute( "id" ) )
                                                                                                {
                                                                                                    console.log( "Elemento que machea es el " + figure ); // window.location.replace("localhost:8080/games/board");
                                                                                                }
                                                                                            }
                                                                                        }
                                                                        )
                                             }
                             )
        </script>