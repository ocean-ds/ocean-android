set -e

LATEST_VERSION_TOKENS_LIB_NPM=`npm show @useblu/ocean-tokens version`

echo "\033[1;31m >>>> Current version is '$LATEST_VERSION_TOKENS_LIB_NPM'. \033[0m"

echo '\033[1;31m >>>> Getting new version and saving on version.properties file. \033[0m'

sed -i-e "/VERSION_NAME=/ s/=.*/=$LATEST_VERSION_TOKENS_LIB_NPM-/g" "../ocean-components/version.properties"

echo '\033[1;31m >>>> Deleting unnecessary temp file. \033[0m'
rm -f "ocean-components/version.properties-e"

echo '\033[1;31m >>>> What is the current DIR?\033[0m'
pwd

echo '\033[1;31m >>>> Downloading tokens into npm lib…\033[0m'
npm install @useblu/ocean-tokens

echo '\033[1;31m >>>> Copying resources XML file \033[0m'
cp node_modules/@useblu/ocean-tokens/android/tokens.xml ../ocean-components/src/main/res/values/tokens.xml

echo '\033[1;31m >>>> Deleting all old font files \033[0m'
rm -rf ../ocean-components/src/main/res/font/*.ttf

echo '\033[1;31m >>>> Copying all font files \033[0m'
cp node_modules/@useblu/ocean-tokens/assets/fonts/*/*.ttf ../ocean-components/src/main/res/font

cd ../ocean-components/src/main/res/font

echo '\033[1;31m >>>> Renaming all font files. \033[0m'
for f in *.ttf
do
	mv -f $f $(echo $f |
	sed -e 's/Avenir/font_family_highlight/g' |
	sed -e 's/NunitoSans/font_family_base/g' |
	sed -e 's/-/_/g' |
	sed -e 's/Roman/Regular/g' |
	sed -e 's/SemiBold/Medium/g' |
	sed -e 's/Heavy/Bold/g' |
  sed -e 's/Black/Extrabold/g' |
	tr '[:upper:]' '[:lower:]')
done