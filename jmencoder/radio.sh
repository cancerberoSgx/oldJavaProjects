##################################
#!/bin/bash
## Script para sintonizar radios uruguayas.
## Gustavo Azambuja - Linux User: 275813
## http://www.twitter.com/gazambuja

## Funciones
function lista {
	radio=`zenity --list --title "Radios Uruguayas" --text "Seleccione la
radio a escuchar" --radiolist --column "Escuchar" --column "Radio" \
	FALSE "El Espectador" \
	FALSE "Oceano FM" \
	FALSE "Futura FM" \
	FALSE "Inolvidable FM" \
	FALSE "CX12 Oriental" \
	FALSE "Monte Carlo" \
	FALSE "Sodre - Emisora del SUR 94.7 FM" \
	FALSE "Sodre - Clasica 650 AM" \
	FALSE "Sodre - Babel 97.1 FM" \
	FALSE "Sodre - Uruguay 1050 AM" \
	FALSE "Sodre - Clasica 650 AM" \
	--height=350 --width=300`

	echo "El usuario elijio: $radio"
	buscar
}

function escuchar {
	echo "Abriendo $radio"
	xterm -geometry 70x3 -e mplayer -loop 0 $1;
}

function buscar {
	case "$radio" in
	"El Espectador")
		escuchar "http://streaming.espectador.com/envivo"
		;;
	"Oceano FM")
		escuchar "http://radio2.oceanofm.com:8010/"
		;;
	"Futura FM")
		escuchar "http://rfm.radio.netgate.com.uy:8000/futurafm"
		;;
	"Inolvidable FM")
		escuchar "mms://inolvidable.audio-server.com/inolvidable/.wma"
		;;
	"CX12 Oriental")
		escuchar "mms://oriental.audio-server.com/oriental"
		;;
	"Monte Carlo")
		escuchar "mms://radio.audio-server.com/montecarlo1"
		;;
	"Nuevo Tiempo")
		escuchar "http://66.98.168.9:9050"
		;;
	"Sodre - Emisora del SUR 94.7 FM")
		escuchar "http://66.55.139.217:9160/"
		;;
	"Sodre - Clasica 650 AM")
		escuchar "http://66.55.139.217:9090/"
		;;
	"Sodre - Babel 97.1 FM")
		escuchar "http://66.55.139.217:9170/"
		;;
	"Sodre - Uruguay 1050 AM")
		escuchar "http://66.55.139.217:9120/"
		;;
	*)
		exit 0
	esac
}

## Main
while true; do
	lista
done
##################################