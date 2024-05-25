# Sistemi i Menaxhimit të Konsulltimeve në FIEK

Ky është një sistem për menaxhimin e konsultimeve në Fakultetin e Inxhinierisë Elektrike dhe Kompjuterike (FIEK), i ndërtuar duke përdorur gjuhët Java dhe JavaFX. Projekti është zhvilluar duke përdorur Intellij IDEA dhe SceneBuilder.

## Përmbajtja
- [Karakteristikat](#karakteristikat)
- [Instalimi](#instalimi)
- [Përdorimi](#përdorimi)
- [Struktura e Projektit](#struktura-e-projektit)
- [Kontribiumi](#kontribuimi)


## Karakteristikat
### Karakteristikat e Përgjithshme

- **Ndërfaqe përdoruesi intuitive:** ApKontribuimilikacioni ofron një ndërfaqe përdoruesi të thjeshtë dhe miqësore, e ndërtuar me JavaFX.
- **Siguria e të dhënave:** Të dhënat e përdoruesve dhe fjalëkalimet ruhen në mënyrë të sigurtë.
- **Njoftimet në kohë reale:** Të dy studentët dhe profesorët marrin njoftime për veprimet e rëndësishme brenda sistemit.
- **Përdorimi i SceneBuilder:** Skemat e ndërfaqes janë krijuar dhe modifikuar me SceneBuilder për një zhvillim të shpejtë dhe efikas të UI-së.

### Për Studentin
- Mund të shikojë datat e konsultimeve.
- Mund të sugjerojë data për konsultime.
- Mund të shikojë profilin e tij/saj dhe të ndryshojë fjalëkalimin.
- Mund të regjistrohet (signup) dhe të bëjë login.

### Për Profesorin
- Mund të japë data për konsultime.
- Mund të marrë njoftime nga studentët për sugjerime të reja.
- Mund të shikojë profilin e tij/saj dhe të ndryshojë fjalëkalimin.
- Llogaritë e profesorëve krijohen nga fakulteti dhe ata mund të bëjnë login.

## Instalimi

1. Klononi këtë repository:
    ```sh
    git clone https://github.com/username/sistemi-konsulltimeve.git
    cd sistemi-konsulltimeve
    ```

2. Hapni projektin në Intellij IDEA.

3. Sigurohuni që të keni Java Development Kit (JDK) të instaluar në sistemin tuaj.

4. Përdorni SceneBuilder për të modifikuar dhe parë skenat FXML në projekt.

5. Ekzekutoni projektin duke përdorur Intellij IDEA.

## Përdorimi

1. **Për Studentin:**
    - Hapni aplikacionin dhe klikoni në "Signup" për të krijuar një llogari të re.
    - Pasi të krijoni llogarinë, mund të bëni login duke përdorur kredencialet tuaja.
    - Pas logimit, mund të shikoni datat e konsultimeve, të sugjeroni data të reja dhe të shikoni ose të ndryshoni profilin tuaj.

2. **Për Profesorin:**
    - Hapni aplikacionin dhe bëni login duke përdorur kredencialet që ju janë dhënë nga fakulteti.
    - Pasi të bëni login, mund të vendosni data të reja për konsultime dhe të merrni njoftime për sugjerimet e reja nga studentët.
    - Gjithashtu, mund të shikoni dhe të ndryshoni profilin tuaj.

## Struktura e Projektit

- `src/main/java`: Përmban kodin burimor të Java për aplikacionin.
- `src/main/resources`: Përmban skedarët FXML dhe burimet tjera si imazhet dhe skedarët CSS.
- `lib`: Përmban bibliotekat e jashtme të përdorura në projekt.

## Kontribuimi

Ky projekt eshte implementuar nga studentet: Dea Llapatinca, Dea Limoni, Blerta Lutolli, Elisa Gjurkaj, Elisa Berisha dhe Alfred Palokaj. [Shiko me shume!](https://github.com/ll-dea/KNK_2024/graphs/contributors)

