#!/bin/sh
set -o errexit

BASEDIR=$(dirname "$0")"/.."
cd "$BASEDIR"

echo "Building application"

./back-end/build.sh

echo "Running tests"

./back-end/run-tests.sh
