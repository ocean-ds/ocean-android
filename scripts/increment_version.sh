set -e

LATEST_VERSION_TOKENS_LIB_NPM=`npm show @useblu/ocean-tokens version`

echo "\033[1;31m >>>> Current version is '$LATEST_VERSION_TOKENS_LIB_NPM'. \033[0m"

echo '\033[1;31m >>>> Getting new version and saving on version.properties file. \033[0m'

sed -i-e "/VERSION_NAME=/ s/=.*/=$LATEST_VERSION_TOKENS_LIB_NPM-/g" "../ocean-components/version.properties"

echo '\033[1;31m >>>> Deleting unnecessary temp file. \033[0m'
rm -f "ocean-components/version.properties-e"