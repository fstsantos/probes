#!/bin/bash

PLANE_ID=`curl -s "http://localhost:8080/plane?x=5&y=5" -X PUT | sed 's/.*\"id\":\"\([^\"]*\)\".*/\1/'`

echo "Plano criado ($PLANE_ID)"

PROBE_1_ID=`curl -s "http://localhost:8080/plane/$PLANE_ID/probe?x=1&y=2&direction=N" -X PUT | sed 's/.*\"id\":\"\([^\"]*\)\".*/\1/'`

echo "Sonda 1 criada ($PROBE_1_ID)"

curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/turnLeft"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/turnLeft"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/turnLeft"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/turnLeft"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_1_ID/move"

PROBE_2_ID=`curl -s "http://localhost:8080/plane/$PLANE_ID/probe?x=3&y=3&direction=E" -X PUT | sed 's/.*\"id\":\"\([^\"]*\)\".*/\1/'`

echo "Sonda 2 criada ($PROBE_2_ID)"

curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/turnRight"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/turnRight"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/move"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/turnRight"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/turnRight"
curl -s "http://localhost:8080/plane/$PLANE_ID/probe/$PROBE_2_ID/move"

echo "Posicao final Sonda 1:"

curl -s "http://localhost:8080/probe/$PROBE_1_ID"

echo
echo "Posicao final Sonda 2:"

curl -s "http://localhost:8080/probe/$PROBE_2_ID"

curl -s "http://localhost:8080/probe/$PROBE_1_ID" -X DELETE
curl -s "http://localhost:8080/probe/$PROBE_2_ID" -X DELETE
curl -s "http://localhost:8080/plane/$PLANE_ID" -X DELETE

echo
