class GerenteExperienciaGastronomica:
    def __init__(self):
        self.menu = {
            "Pizza": 10.0,
            "Hamburguesa": 8.0,
            "Ensalada": 6.0,
            "Pastas": 12.0
        }
        self.categoria_especial = "Especiales del Chef"
        self.precios_especiales = {
            "Sushi": 15.0,
            "Camarones al Ajillo": 18.0,
            "Tiramisú": 8.0
        }
        self.max_comidas = 100
        self.descuento_oferta_especial = {
            50: 10,
            100: 25
        }
        self.descuento_cantidad_comidas = {
            5: 0.9,
            10: 0.8
        }

    def mostrar_menu(self):
        print("Menú:")
        for comida, precio in self.menu.items():
            print(f"{comida} - ${precio}")

        print(f"Categoría especial: {self.categoria_especial}")
        for comida, precio in self.precios_especiales.items():
            print(f"{comida} - ${precio}")

    def validar_cantidad(self, cantidad):
        try:
            cantidad = int(cantidad)
            if cantidad <= 0:
                raise ValueError
            return cantidad
        except ValueError:
            return None

    def calcular_costo_total(self, pedido):
        costo_total = 0.0
        for comida, cantidad in pedido.items():
            if comida in self.menu:
                costo_total += self.menu[comida] * cantidad
            elif comida in self.precios_especiales:
                costo_total += self.precios_especiales[comida] * cantidad

        cantidad_total = sum(pedido.values())
        if cantidad_total > 5 and cantidad_total <= 10:
            costo_total *= self.descuento_cantidad_comidas[5]
        elif cantidad_total > 10:
            costo_total *= self.descuento_cantidad_comidas[10]

        for min_costo, descuento in self.descuento_oferta_especial.items():
            if costo_total > min_costo:
                costo_total -= descuento
                break

        return costo_total

    def realizar_pedido(self):
        print("Bienvenido a la Experiencia Gastronómica")
        self.mostrar_menu()

        pedido = {}
        cantidad_total = 0

        while True:
            comida = input("Ingrese la comida que desea ordenar (o 'fin' para finalizar el pedido): ")
            if comida == "fin":
                break

            if comida not in self.menu and comida not in self.precios_especiales:
                print("La comida ingresada no está en el menú. Por favor, intente nuevamente.")
                continue

            cantidad = input(f"Ingrese la cantidad de {comida} que desea ordenar: ")
            cantidad = self.validar_cantidad(cantidad)
            if cantidad is None:
                print("La cantidad debe ser un número entero positivo mayor que cero. Por favor, intente nuevamente.")
                continue

            cantidad_total += cantidad
            if cantidad_total > self.max_comidas:
                print("El pedido supera la cantidad máxima permitida de 100 comidas. Por favor, reduzca la cantidad o finalice el pedido.")
                cantidad_total -= cantidad
                continue

            pedido[comida] = pedido.get(comida, 0) + cantidad

        if not pedido:
            print("El pedido ha sido cancelado.")
            return -1

        costo_total = self.calcular_costo_total(pedido)

        print("Resumen del pedido:")
        for comida, cantidad in pedido.items():
            print(f"{cantidad} {comida} - ${cantidad * (self.menu.get(comida) or self.precios_especiales.get(comida))}")

        print(f"Costo total: ${costo_total:.2f}")
        return int(costo_total)


if __name__ == "__main__":
    gerente = GerenteExperienciaGastronomica()
    costo_total = gerente.realizar_pedido()
