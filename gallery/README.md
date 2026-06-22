# Banner Storybook Gallery — render real via WebView

Galeria web que mostra o **componente Banner nativo de verdade** (iOS + Android lado a lado),
acessível por qualquer browser/WebView **sem ninguém precisar rodar Xcode ou Android Studio**.

O render nativo acontece **uma vez, no CI**; a saída é um site estático de imagens + controles.

## Como funciona

```
┌─ ocean-android ─ OceanBannerSnapshotTest.kt ─┐   renderiza OceanBanner real (Robolectric)
│                                               ├─► PNG  banner__<key>.png
└─ ocean-ios ───── BannerSnapshotTests.swift ──┘   renderiza OceanSwiftUI.Banner real (XCTest)

        CI (banner-gallery.yml) coleta os PNGs ─► gallery/images/{android,ios}/<key>.png
        e publica gallery/ no GitHub Pages ─────► abrir o link no browser/WebView
```

A galeria (`index.html`) tem os mesmos knobs do Storybook nativo (Size, Type, Image, Buttons).
Cada combinação carrega o PNG real correspondente das duas plataformas.

### Esquema de chave (contrato compartilhado)

```
banner__{size}__{type}__{image}__{buttons}
  size    = large | small
  type    = default | warning | negative | emphasys
  image   = noimage | image
  buttons = none | one | two
```

São 2×4×2×3 = **48 combinações** por plataforma. As listas em `OceanBannerSnapshotTest.kt`
(`buildBannerMatrix`) e `BannerSnapshotTests.swift` (`BannerSnapshotMatrix`) **devem ficar em sincronia**.
`title`/`description` são fixos (presets) — texto livre só num render ao vivo.

## Publicar (CI)

1. Em **Settings > Pages**, selecione **Source: GitHub Actions** (uma vez).
2. Rode o workflow **Banner Storybook Gallery** (`Actions` > Run workflow), ou faça push nas paths monitoradas.
3. Ao fim, o link do Pages aparece no job `publish` (`environment: github-pages`).

> O job iOS roda em runner macOS e faz checkout do `ocean-ds/ocean-ios` na branch `feat/ocean-banner-component`.
> Ajuste a `ref` quando o Banner for mergeado em `main`/`develop`.

## Gerar localmente (quem tem o toolchain)

```bash
# Android → ocean-android/ocean-components/build/outputs/banner-snapshots/*.png
./gradlew :ocean-components:testDebugUnitTest --tests "br.com.useblu.oceands.snapshot.OceanBannerSnapshotTest"
cp ocean-components/build/outputs/banner-snapshots/*.png gallery/images/android/

# iOS → ocean-ios/OceanDesignSystemTests/__BannerSnapshots__/*.png
#   (rode BannerSnapshotTests no Xcode, ou via xcodebuild)
cp ../ocean-ios/OceanDesignSystemTests/__BannerSnapshots__/*.png gallery/images/android/../ios/

# Visualizar a galeria
cd gallery && python3 -m http.server 8080   # abrir http://localhost:8080
```

## Adicionar um novo componente

1. Crie `<Componente>SnapshotTest.kt` / `<Componente>SnapshotTests.swift` espelhando a matriz.
2. Use o mesmo esquema de chave `comp__dim1__dim2__...`.
3. Adicione os knobs do componente em `index.html`.

## Limitações honestas

- **Não é interativo de verdade**: knobs = combinações pré-renderizadas; texto livre vira preset.
  Interação total no browser exige um device rodando (ex.: Appetize).
- **Robolectric (Android)** e o **render off-screen (iOS)** podem divergir minimamente de um device
  físico (antialiasing/fontes). Mas é o **código real do componente** — divergências de layout, cor e
  dimensão são fiéis (é justamente isso que esta galeria expõe, ao contrário de uma maquete HTML).
- Versões/CI podem precisar de ajuste fino no primeiro run (ver notas no `.github/workflows/banner-gallery.yml`).
