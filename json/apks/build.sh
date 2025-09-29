#sh
#rm -rf ./root
./clear.sh
buildtoolsPath="/Users/huhuijie/Library/Android/sdk/build-tools/34.0.0/";

apktool d root.apk
apktool b root
rm -rf input.apk
mv ./root/dist/root.apk ./input.apk
#java -jar apktool1.jar d root.apk
#java -jar apktool1.jar b root
${buildtoolsPath}zipalign -p -f -v 4 input.apk zipalign.apk;
## /Users/huhuijie/Library/Android/sdk/build-tools/34.0.0/apksigner sign --ks-pass test.jks pass:123456 --out output.apk input1.apk;
## apksigner sign --ks your_key.keystore --ks-pass pass:your_password --out signed_apk.apk aligned_apk.apk
${buildtoolsPath}apksigner sign --ks test.jks --ks-pass pass:123456 --out output.apk zipalign.apk
echo '--------------------apksigner-----------------------------------'
${buildtoolsPath}apksigner verify --verbose output.apk;
echo '--------------------packageName----------------------------0-------'
${buildtoolsPath}aapt dump badging output.apk | grep "package: name" 
echo '--------------------packageName----------------------------1-------'

adb install -r output.apk;

#adb logcat | grep "com.roast.non.beauty"